import { test, expect } from '@playwright/test';

// TodoMVC url for testing
const TODOMVC_URL = 'https://demo.playwright.dev/todomvc';

test.describe('TodoMVC - basic todo flow', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto(TODOMVC_URL);
  });

  test('add todos, complete one, and filter the list', async ({ page }) => {
    const newTodoInput = page.getByPlaceholder('What needs to be done?');
    const todoItems = page.getByTestId('todo-item');

    // Add the first todo and verify if it appears in the list
    await newTodoInput.fill('Learn Playwright');
    await newTodoInput.press('Enter');
    await expect(todoItems).toHaveText(['Learn Playwright']);

    // Add a second todo and verify there are now 2 items
    await newTodoInput.fill('Write tests');
    await newTodoInput.press('Enter');
    await expect(todoItems).toHaveText(['Learn Playwright', 'Write tests']);

    // Mark "Learn Playwright" as completed
    const firstTodo = todoItems.filter({ hasText: 'Learn Playwright' });
    await firstTodo.getByRole('checkbox').check();
    await expect(firstTodo).toHaveClass(/completed/);

    // "Completed" filter shows exactly 1 item
    await page.getByRole('link', { name: 'Completed' }).click();
    await expect(todoItems).toHaveCount(1);
    await expect(todoItems).toHaveText(['Learn Playwright']);

    // "Active" filter shows only "Write tests"
    await page.getByRole('link', { name: 'Active' }).click();
    await expect(todoItems).toHaveCount(1);
    await expect(todoItems).toHaveText(['Write tests']);

    // Back to "All" so the clear-completed step below sees the full list
    await page.getByRole('link', { name: 'All' }).click();
    await expect(todoItems).toHaveCount(2);

    // (Optional) Clear completed items and verify the list updates
    await page.getByRole('button', { name: 'Clear completed' }).click();
    await expect(todoItems).toHaveCount(1);
    await expect(todoItems).toHaveText(['Write tests']);
  });
});




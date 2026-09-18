import { test, expect } from '@playwright/test';

test('assignment- todomvc app', async ({ page }) =>  {
  // Open the TodoMVC url
  await page.goto('https://demo.playwright.dev/todomvc/#/');

// Add the first todo
  await page.getByRole('textbox', { name: 'What needs to be done?' }).click();
  await page.getByRole('textbox', { name: 'What needs to be done?' }).fill('Learn Playwright');
  await page.getByRole('textbox', { name: 'What needs to be done?' }).press('Enter');

  await expect(page.getByTestId('todo-title')).toContainText('Learn Playwright');

  // Add the second todo
  await page.getByRole('textbox', { name: 'What needs to be done?' }).click();
  await page.getByRole('textbox', { name: 'What needs to be done?' }).fill('Write tests');
  await page.getByRole('textbox', { name: 'What needs to be done?' }).press('Enter');
  
  await expect(page.getByText('Write tests')).toBeVisible();
  await expect(page.locator('body')).toContainText('Write tests');

// Completed the "Learn Playwright" todo
  await page.getByRole('listitem').filter({ hasText: 'Learn Playwright' }).getByLabel('Toggle Todo').check();
  await page.getByRole('link', { name: 'Completed' }).click();
  await expect(page.getByText('All Active Completed')).toBeVisible();

// View active todos and verify "Write tests"
  await page.getByRole('link', { name: 'Active' }).click();
  await expect(page.getByTestId('todo-title')).toContainText('Write tests');

  // Clear the completed todo
  await page.getByRole('button', { name: 'Clear completed' }).click();
  // Verify only the active todo remains
  await page.getByRole('link', { name: 'All' }).click();
  await expect(page.getByText('Write tests')).toBeVisible();
  await expect(page.getByText('This is just a demo of TodoMVC for testing, not the real TodoMVC app. todosMark')).toBeVisible();
});
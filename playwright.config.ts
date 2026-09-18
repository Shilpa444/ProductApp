import { defineConfig, devices } from '@playwright/test';

/**

* See https://playwright.dev/docs/test-configuration.
  */
  export default defineConfig({
  testDir: './tests',

/* Run tests in parallel */
fullyParallel: true,

/* Reporter to use */
reporter: 'html',

/* Shared settings for all tests */
use: {
/* Collect trace when retrying the failed test */
trace: 'on-first-retry',
launchOptions: {
  slowMo: 1000, // Slow down by 1 second
}
},

/* Configure projects for Chromium and Firefox only */
projects: [
{
name: 'chromium',
use: { ...devices['Desktop Chrome'] },
},

{
  name: 'firefox',
  use: { ...devices['Desktop Firefox'] },
},


],
});

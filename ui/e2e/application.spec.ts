import { test, expect } from "@playwright/test";

test("Adding application", async ({ page }) => {
  await page.goto("http://localhost:3000/");

  await page.getByRole("button", { name: "Lisa uus" }).click();

  await page.getByLabel("Nimi").click();
  await page.getByLabel("Nimi").fill("Uus rakendus");

  await page.getByLabel("Grupp").click();
  await page.getByLabel("USER_INTERFACE").getByText("USER_INTERFACE").click();

  await page.getByLabel("Tüüp").click();
  await page.getByLabel("JAVA").click();

  await page.getByLabel("Kirjeldus").click();
  await page.getByLabel("Kirjeldus").fill("Uue rakenduse kirjeldus");

  await page.getByLabel("Arenduskulu").click();
  await page.getByLabel("Arenduskulu").fill("-1");

  await page.getByRole("button", { name: "Sisesta" }).click();

  await expect(page.getByText("Number must be greater than")).toBeVisible();
});

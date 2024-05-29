import { expect, test, vi } from "vitest";
import { createApiUrl } from "@/actions/utils";

test("create api url with params", () => {
  vi.stubEnv("API_URL", "http://localhost:8080");

  expect(createApiUrl("/application", { name: "name" }).toString()).toBe(
    "http://localhost:8080/application?name=name",
  );
});

import { expect, describe, test, vi } from "vitest";
import {
  createApiUrl,
  parseGetResponse,
  parsePostResponse,
} from "@/actions/utils";

describe("parse post response", () => {
  test("not ok", async () => {
    const response = {
      ok: false,
      text: () => Promise.resolve("OK!"),
    } as any;

    const parsed = await parsePostResponse(response);

    expect(parsed.ok).toBe(response.ok);
    expect(parsed.error).toBe(await response.text());
  });

  test("not ok (empty response)", async () => {
    const response = undefined as any;

    await expect(() => parsePostResponse(response)).rejects.toThrowError(
      "Response can't be undefined!",
    );
  });
});

describe("parse get response", () => {
  test("ok", async () => {
    type Test = {
      name: string;
    };

    const data = {
      name: "ok",
    };

    const response = {
      ok: true,
      json: () => Promise.resolve(data),
    } as any;

    const parsed = await parseGetResponse<Test>(response);

    expect(parsed.ok).toBe(response.ok);
    expect(parsed.data).toBe(data);
  });

  test("not ok", async () => {
    const response = {
      ok: false,
      text: () => Promise.resolve("not ok!"),
    } as any;

    const parsed = await parseGetResponse(response);

    expect(parsed.ok).toBe(response.ok);
    expect(parsed.error).toBe(await response.text());
  });

  test("not ok (empty response)", async () => {
    const response = undefined as any;

    await expect(() => parseGetResponse(response)).rejects.toThrowError(
      "Response can't be undefined!",
    );
  });
});

test("create api url with params", () => {
  vi.stubEnv("API_URL", "http://localhost");

  expect(createApiUrl("/application", { name: "name" }).toString()).toBe(
    "http://localhost/application?name=name",
  );
});

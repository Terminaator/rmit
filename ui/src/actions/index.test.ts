import { describe, expect, test, vi } from "vitest";
import { get, post } from "@/actions";
import { GENERIC_ERROR_MESSAGE } from "@/actions/constants";

describe("post", () => {
  test("ok", async () => {
    vi.stubEnv("API_URL", "http://localhost");

    const ok = true;
    global.fetch = vi.fn().mockResolvedValueOnce({
      ok: ok,
    });

    const response = await post("/application");

    expect(response.ok).toBe(ok);
  });

  test("not ok", async () => {
    vi.stubEnv("API_URL", "http://localhost");

    const ok = false;
    const error = "not ok";
    global.fetch = vi.fn().mockResolvedValueOnce({
      ok: ok,
      text: () => Promise.resolve(error),
    });

    const response = await post("/application");

    expect(response.ok).toBe(ok);
    expect(response.error).toBe(error);
  });

  test("not ok error while parsing", async () => {
    vi.stubEnv("API_URL", "http://localhost");

    const ok = false;
    global.fetch = vi.fn().mockResolvedValueOnce({
      ok: ok,
    });

    const response = await post("/application");

    expect(response.ok).toBe(ok);
    expect(response.error).toBe(GENERIC_ERROR_MESSAGE);
  });
});

describe("get", () => {
  test("ok", async () => {
    vi.stubEnv("API_URL", "http://localhost");

    const ok = true;
    const data = { name: "ok" };
    global.fetch = vi.fn().mockResolvedValueOnce({
      ok: ok,
      json: () => Promise.resolve(data),
    });

    const response = await get("/application");

    expect(response.ok).toBe(ok);
    expect(response.data).toBe(data);
  });

  test("not ok", async () => {
    vi.stubEnv("API_URL", "http://localhost");

    const ok = false;
    const error = "not ok";
    global.fetch = vi.fn().mockResolvedValueOnce({
      ok: ok,
      text: () => Promise.resolve(error),
    });

    const response = await get("/application");

    expect(response.ok).toBe(ok);
    expect(response.error).toBe(error);
  });

  test("not ok error while parsing", async () => {
    vi.stubEnv("API_URL", "http://localhost");

    const ok = false;
    global.fetch = vi.fn().mockResolvedValueOnce({
      ok: ok,
    });

    const response = await get("/application");

    expect(response.ok).toBe(ok);
    expect(response.error).toBe(GENERIC_ERROR_MESSAGE);
  });
});

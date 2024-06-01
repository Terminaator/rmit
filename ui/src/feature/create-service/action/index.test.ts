import { describe, expect, test, vi } from "vitest";
import { createServiceActionWithoutRevalidatePath } from "@/feature/create-service/action/index";
import { ERROR, SUCCESS, UNSET } from "@/components/data/Form/constant";
import { DataFormState } from "@/components/data/Form/type";
import { VALIDATION_ERROR } from "@/actions/constants";
import { PostResponse } from "@/actions/types";
import { SERVICE_CREATED } from "@/feature/create-service/action/constant";

const { mockedPost } = vi.hoisted(() => {
  return {
    mockedPost: vi.fn(),
  };
});

vi.mock("@/actions", async (importOriginal) => {
  const mod = await importOriginal<typeof import("@/actions")>();

  return {
    ...mod,
    post: mockedPost,
  };
});

describe("create service action", () => {
  test("parse failure", async () => {
    const state: DataFormState = {
      status: UNSET,
    };
    const data: FormData = new FormData();

    const response = await createServiceActionWithoutRevalidatePath(
      state,
      data,
    );

    expect(response.status).toBe(ERROR);
    expect(response.message).toBe(VALIDATION_ERROR);
  });

  test("request failure", async () => {
    const state: DataFormState = {
      status: UNSET,
    };

    const data: FormData = new FormData();
    data.set("appCode", "e74cda1c-6311-463d-8291-87164a6404a2");
    data.set("name", "valid service");

    const postResponse: PostResponse = {
      ok: false,
    };

    mockedPost.mockResolvedValueOnce(postResponse);

    const response = await createServiceActionWithoutRevalidatePath(
      state,
      data,
    );

    expect(response.status).toBe(ERROR);
  });

  test("success", async () => {
    const state: DataFormState = {
      status: UNSET,
    };

    const data: FormData = new FormData();
    data.set("appCode", "e74cda1c-6311-463d-8291-87164a6404a2");
    data.set("name", "valid service");

    const postResponse: PostResponse = {
      ok: true,
    };

    mockedPost.mockResolvedValueOnce(postResponse);

    const response = await createServiceActionWithoutRevalidatePath(
      state,
      data,
    );

    expect(response.status).toBe(SUCCESS);
    expect(response.message).toBe(SERVICE_CREATED);
  });
});

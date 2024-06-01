"use server";

import { APPLICATION_CREATED } from "@/feature/create-application/action/constant";
import { ERROR, SUCCESS } from "@/components/data/Form/constant";
import { VALIDATION_ERROR } from "@/actions/constants";
import { post } from "@/actions";
import {
  ERROR_STATUS,
  DataFormState,
  SUCCESS_STATUS,
} from "@/components/data/Form/type";
import { createApplicationSchema } from "@/feature/create-application/schema";
import { revalidatePath } from "next/cache";

const parseForm = (form: FormData) => {
  const data = Object.fromEntries(form);
  const parsed = createApplicationSchema.safeParse(data);

  return {
    success: parsed.success,
    data: parsed.success ? parsed.data : undefined,
  };
};

const createActionResponse = (
  state: DataFormState,
  status: ERROR_STATUS | SUCCESS_STATUS,
  message: string,
): DataFormState => {
  return {
    ...state,
    status: status,
    message: message,
  };
};

export const createApplicationActionWithoutRevalidatePath = async (
  state: DataFormState,
  form: FormData,
): Promise<DataFormState> => {
  const parsed = parseForm(form);

  console.log("createApplicationAction", parsed.data);

  if (!parsed.success) {
    return createActionResponse(state, ERROR, VALIDATION_ERROR);
  }

  const response = await post("/application", parsed.data);

  if (!response.ok) {
    return createActionResponse(state, ERROR, response.error!);
  }

  return createActionResponse(state, SUCCESS, APPLICATION_CREATED);
};

export const createApplicationAction = async (
  state: DataFormState,
  form: FormData,
): Promise<DataFormState> => {
  const response = await createApplicationActionWithoutRevalidatePath(
    state,
    form,
  );

  if (response.status === SUCCESS) {
    revalidatePath("/");
  }

  return response;
};

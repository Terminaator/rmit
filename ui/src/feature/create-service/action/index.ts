"use server";

import { SERVICE_CREATED } from "@/feature/create-service/action/constant";
import {
  ERROR_STATUS,
  DataFormState,
  SUCCESS_STATUS,
} from "@/components/data/Form/type";
import { ERROR, SUCCESS } from "@/components/data/Form/constant";
import { VALIDATION_ERROR } from "@/actions/constants";
import { post } from "@/actions";
import { createServiceSchema } from "@/feature/create-service/schema";
import { revalidatePath } from "next/cache";

const parseForm = (form: FormData) => {
  const data = Object.fromEntries(form);
  const parsed = createServiceSchema.safeParse(data);

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

export const createServiceActionWithoutRevalidatePath = async (
  state: DataFormState,
  form: FormData,
): Promise<DataFormState> => {
  const parsed = parseForm(form);

  console.log("createServiceAction", parsed.data);

  if (!parsed.success) {
    return createActionResponse(state, ERROR, VALIDATION_ERROR);
  }

  const response = await post("/application/service", parsed.data);

  if (!response.ok) {
    return createActionResponse(state, ERROR, response.error!);
  }

  return createActionResponse(state, SUCCESS, SERVICE_CREATED);
};

export const createServiceAction = async (
  state: DataFormState,
  form: FormData,
): Promise<DataFormState> => {
  const response = await createServiceActionWithoutRevalidatePath(state, form);

  if (response.status === SUCCESS) {
    revalidatePath("/service");
  }

  return response;
};

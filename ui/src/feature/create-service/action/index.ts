"use server";

import { SERVICE_CREATED } from "@/feature/create-service/action/constant";
import { createServiceFields } from "@/feature/create-service/schema";
import { CreateFormState } from "@/components/data/Form/type";
import {
  createActionResponse,
  parseForm,
} from "@/feature/create-service/action/util";
import { ERROR, SUCCESS } from "@/components/data/Form/constant";
import { VALIDATION_ERROR } from "@/actions/constants";
import { post } from "@/actions";

export const createServiceAction = async (
  state: CreateFormState,
  form: FormData,
): Promise<CreateFormState> => {
  const parsed = parseForm(createServiceFields, form);

  if (!parsed.success) {
    return createActionResponse(state, ERROR, VALIDATION_ERROR);
  }

  const response = await post("/application/service", parsed.data);

  if (!response.ok) {
    return createActionResponse(state, ERROR, response.error!);
  }

  return createActionResponse(state, SUCCESS, SERVICE_CREATED, "/service");
};

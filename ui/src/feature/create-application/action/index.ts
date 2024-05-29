"use server";

import { APPLICATION_CREATED } from "@/feature/create-application/action/constant";
import { createApplicationFields } from "@/feature/create-application/schema";
import {
  createActionResponse,
  parseForm,
} from "@/feature/create-application/action/util";
import { ERROR, SUCCESS } from "@/components/data/Form/constant";
import { VALIDATION_ERROR } from "@/actions/constants";
import { post } from "@/actions";
import { CreateFormState } from "@/components/data/Form/type";

export const createApplicationAction = async (
  state: CreateFormState,
  form: FormData,
): Promise<CreateFormState> => {
  const parsed = parseForm(createApplicationFields, form);

  if (!parsed.success) {
    return createActionResponse(state, ERROR, VALIDATION_ERROR);
  }

  const response = await post("/application", parsed.data);

  if (!response.ok) {
    return createActionResponse(state, ERROR, response.error!);
  }

  return createActionResponse(state, SUCCESS, APPLICATION_CREATED, "/");
};

import { CreateFormState } from "@/components/data/Form/type";

export const UNSET = "UNSET";
export const SUCCESS = "SUCCESS";
export const ERROR = "ERROR";

export const UNSET_CREATE_FORM_STATE: CreateFormState = {
  status: UNSET,
  message: undefined,
};

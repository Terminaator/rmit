import { DataFormState } from "@/components/data/Form/type";

export const UNSET = "UNSET";
export const SUCCESS = "SUCCESS";
export const ERROR = "ERROR";

export const UNSET_DATA_FORM_STATE: DataFormState = {
  status: UNSET,
  message: undefined,
};

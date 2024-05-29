import { ZodType } from "zod";

export type UNSET_STATUS = "UNSET";
export type SUCCESS_STATUS = "SUCCESS";
export type ERROR_STATUS = "ERROR";

export type CreateFormState = {
  status: UNSET_STATUS | SUCCESS_STATUS | ERROR_STATUS;
  message?: string;
};

export type DataFormProps = {
  // eslint-disable-next-line no-unused-vars
  action: (state: CreateFormState, form: FormData) => Promise<CreateFormState>;
  close: () => void;
  fields: {
    name: string;
    label: string;
    description: string;
    type: ZodType;
    defaultValue: string;
  }[];
};
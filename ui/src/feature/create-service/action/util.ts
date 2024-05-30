import {
  CreateFormState,
  DataFormFieldType,
  ERROR_STATUS,
  SUCCESS_STATUS,
} from "@/components/data/Form/type";
import { SUCCESS } from "@/components/data/Form/constant";
import { revalidatePath } from "next/cache";
import { createSchema } from "@/lib/utils";

const parseDataValue = (data: any) => {
  return data !== "" ? data : null;
};

const parseData = (data: { [key: string]: any }) => {
  return Object.keys(data).reduce(
    (obj: { [key: string]: any }, key) => ({
      ...obj,
      [key]: parseDataValue(data[key]),
    }),
    {},
  );
};

export const parseForm = (fields: DataFormFieldType[], form: FormData) => {
  const data = Object.fromEntries(form);
  const parsed = createSchema(fields).safeParse(data);

  return {
    success: parsed.success,
    data: parseData(parsed.data!),
  };
};

export const createActionResponse = (
  state: CreateFormState,
  status: ERROR_STATUS | SUCCESS_STATUS,
  message: string,
  path?: string,
): CreateFormState => {
  if (status === SUCCESS) {
    revalidatePath(path!);
  }

  return {
    ...state,
    status: status,
    message: message,
  };
};

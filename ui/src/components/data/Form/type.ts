export type UNSET_STATUS = "UNSET";
export type SUCCESS_STATUS = "SUCCESS";
export type ERROR_STATUS = "ERROR";

export type DataFormState = {
  status: UNSET_STATUS | SUCCESS_STATUS | ERROR_STATUS;
  message?: string;
};

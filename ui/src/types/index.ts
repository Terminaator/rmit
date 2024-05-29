export type Params = {
  [key: string]: string | string[] | undefined;
};

export type Application = {
  name: string;
  group: string;
  type: string;
  description: string;
  cost: string;
  modified: string;
} & Code;

export type Service = {
  appCode: string;
  name: string;
  type: string;
  subType: string;
  description: string;
  modified: string;
} & Code;

export type Code = {
  code: string;
};

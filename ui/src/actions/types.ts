export type PostResponse = {} & FetchResponse;

export type GetResponse<T> = {
  data?: T;
} & FetchResponse;

export type FetchResponse = {
  ok: boolean;
  error?: string;
};

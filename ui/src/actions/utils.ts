import { GENERIC_ERROR_MESSAGE } from "@/actions/constants";
import { FetchResponse, GetResponse, PostResponse } from "@/actions/types";

export const parsePostResponse = async (
  res: Response,
): Promise<PostResponse> => {
  return {
    ok: res.ok,
    error: !res.ok ? await res.text() : undefined,
  };
};

export const parseError = (): FetchResponse => {
  return {
    ok: false,
    error: GENERIC_ERROR_MESSAGE,
  };
};

export const parseGetResponse = async <T>(
  res: Response,
): Promise<GetResponse<T>> => {
  return {
    ok: res.ok,
    data: res.ok ? await res.json() : undefined,
    error: !res.ok ? await res.text() : undefined,
  };
};

export const createApiUrl = (
  path: string,
  params?: { [key: string]: string },
) => {
  const url = new URL(`${process.env.API_URL}${path}`);

  if (params) {
    Object.keys(params).forEach((key) =>
      url.searchParams.set(key, params[key]),
    );
  }

  return url;
};

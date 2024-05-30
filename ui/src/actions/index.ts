"use server";

import {
  createApiUrl,
  parseError,
  parseGetResponse,
  parsePostResponse,
} from "@/actions/utils";
import { GetResponse, PostResponse } from "@/actions/types";

export const post = async (path: string, body?: any): Promise<PostResponse> => {
  return await fetch(createApiUrl(path), {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  })
    .then((res) => parsePostResponse(res))
    .catch(() => parseError());
};

export const get = async <T>(
  path: string,
  params?: { [key: string]: string },
): Promise<GetResponse<T>> => {
  return fetch(createApiUrl(path, params), { cache: "no-store" })
    .then((res) => {
      return parseGetResponse<T>(res);
    })
    .catch(() => parseError());
};

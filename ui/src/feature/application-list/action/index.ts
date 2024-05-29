"use server";

import { Application } from "@/types";
import { GetResponse } from "@/actions/types";
import { get } from "@/actions";

export const getApplicationsAction = async (
  name: string,
): Promise<GetResponse<Application[]>> => {
  return get<Application[]>("/application", { name: name });
};

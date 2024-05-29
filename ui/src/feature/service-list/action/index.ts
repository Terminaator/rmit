"use server";

import { Service } from "@/types";
import { GetResponse } from "@/actions/types";
import { get } from "@/actions";

export const getServicesAction = async (
  name: string,
): Promise<GetResponse<Service[]>> => {
  return get<Service[]>("/application/service", { name: name });
};

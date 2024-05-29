import { type ClassValue, clsx } from "clsx";
import { twMerge } from "tailwind-merge";
import { z } from "zod";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export const createSchema = (fields: { name: any; type: any }[]) => {
  return z.object(
    Object.fromEntries(fields.map(({ name, type }) => [name, type])),
  );
};

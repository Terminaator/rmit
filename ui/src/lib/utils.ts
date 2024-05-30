import { type ClassValue, clsx } from "clsx";
import { twMerge } from "tailwind-merge";
import { z } from "zod";
import { DataFormFieldType } from "@/components/data/Form/type";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export const createSchema = (fields: DataFormFieldType[]) => {
  return z.object(
    Object.fromEntries(
      fields.map(({ name, validation }) => [name, validation]),
    ),
  );
};

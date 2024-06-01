import { z } from "zod";
import {
  APPLICATION_GROUP_TYPE,
  APPLICATION_TYPE,
} from "@/feature/create-application/type";

export const createApplicationSchema = z.object({
  name: z.string().trim().min(1).max(255),
  group: z.enum(APPLICATION_GROUP_TYPE).nullish(),
  type: z.enum(APPLICATION_TYPE).nullish(),
  description: z
    .string()
    .trim()
    .max(20000)
    .transform((value) => value || undefined)
    .nullish(),
  cost: z
    .literal("")
    .or(z.coerce.number().int().gte(0))
    .transform((value): number | undefined | "" =>
      value !== "" ? value : undefined,
    )
    .nullish(),
});

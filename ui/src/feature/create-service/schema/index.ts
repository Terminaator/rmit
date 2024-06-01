import { z } from "zod";
import { SERVICE_SUB_TYPE, SERVICE_TYPE } from "@/feature/create-service/type";

export const createServiceSchema = z.object({
  appCode: z.string().uuid(),
  name: z.string().trim().min(1).max(255),
  type: z.enum(SERVICE_TYPE).nullish(),
  subType: z.enum(SERVICE_SUB_TYPE).nullish(),
  description: z
    .string()
    .trim()
    .max(20000)
    .transform((value) => value || undefined)
    .nullish(),
});

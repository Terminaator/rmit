import { describe, expect, test } from "vitest";
import { createServiceSchema } from "@/feature/create-service/schema/index";
import { z } from "zod";

const base: z.infer<typeof createServiceSchema> = {
  appCode: "e74cda1c-6311-463d-8291-87164a6404a2",
  name: "valid service",
  type: undefined,
  subType: undefined,
  description: "",
};

describe("create service schema", () => {
  test("initial", () => {
    const parsed = createServiceSchema.safeParse(base);

    expect(parsed.success).toBe(true);

    const data = parsed.data!;
    expect(data.appCode).toBe(base.appCode);
    expect(data.name).toBe(base.name);
    expect(data.type).toBeUndefined();
    expect(data.subType).toBeUndefined();
    expect(data.description).toBeUndefined();
  });

  describe("field", () => {
    test("name", () => {
      [
        {
          valid: true,
          value: "valid name",
          output: "valid name",
        },
        {
          valid: false,
          value: "",
          output: undefined,
        },
        {
          valid: false,
          value: "a".repeat(256),
          output: undefined,
        },
      ].forEach(({ valid, value, output }) => {
        const input = { ...base };
        input.name = value;

        const parsed = createServiceSchema.safeParse(input);

        expect(parsed.success).toBe(valid);

        if (valid) {
          expect(parsed.data?.name).toBe(output);
        }
      });
    });
  });
});

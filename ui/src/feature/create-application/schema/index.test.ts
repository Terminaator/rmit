import { describe, expect, test } from "vitest";
import { z } from "zod";
import { createApplicationSchema } from "@/feature/create-application/schema/index";

const base: z.infer<typeof createApplicationSchema> = {
  name: "VALID application",
  group: undefined,
  type: undefined,
  description: "",
  cost: "",
};

describe("create application schema", () => {
  test("initial", () => {
    const parsed = createApplicationSchema.safeParse(base);

    expect(parsed.success).toBe(true);

    const data = parsed.data!;

    expect(data.name).toBe(base.name);
    expect(data.group).toBeUndefined();
    expect(data.type).toBeUndefined();
    expect(data.description).toBeUndefined();
    expect(data.cost).toBeUndefined();
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

        const parsed = createApplicationSchema.safeParse(input);

        expect(parsed.success).toBe(valid);

        if (valid) {
          expect(parsed.data?.name).toBe(output);
        }
      });
    });
  });
});

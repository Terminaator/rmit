import { z } from "zod";
import { DataFormFieldType } from "@/components/data/Form/type";

const GROUP_TYPE = ["USER_INTERFACE", "BUSINESS_LOGIC", "DATABASE"] as const;
const TYPE = ["JAVA", "PHP"] as const;

export const createApplicationFields: DataFormFieldType[] = [
  {
    name: "name",
    label: "Nimi",
    description: "Rakenduse inimloetav nimetus.",
    type: "INPUT",
    validation: z.string().trim().min(1).max(255),
    defaultValue: "",
  },
  {
    name: "group",
    label: "Grupp",
    description:
      "Grupeerib moodulid (nt kasutajaliides, äriloogika, andmebaas), mis moodustavad ühe rakenduse.",
    type: "SELECT",
    validation: z.literal("").or(z.enum(GROUP_TYPE)),
    defaultValue: "",
    options: GROUP_TYPE,
  },
  {
    name: "type",
    label: "Tüüp",
    description:
      "Rakenduse tüüp või kategooria (java; php; andmebaasimootor; ...).",
    type: "SELECT",
    validation: z.literal("").or(z.enum(TYPE)),
    defaultValue: "",
    options: TYPE,
  },
  {
    name: "description",
    label: "Kirjeldus",
    description: "Rakenduse kirjeldus. Kuni 20000 tähemärki.",
    type: "INPUT",
    validation: z.string().trim().max(20000),
    defaultValue: "",
  },
  {
    name: "cost",
    label: "Arenduskulu",
    description: "Rakenduse arenduskulu.",
    type: "INPUT",
    validation: z.literal("").or(z.coerce.number().int().gte(0)),
    defaultValue: "",
  },
];

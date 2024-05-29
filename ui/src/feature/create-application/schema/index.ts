import { z } from "zod";

export const createApplicationFields = [
  {
    name: "name",
    label: "Nimi",
    description: "Rakenduse inimloetav nimetus.",
    type: z.string().trim().min(1).max(255),
    defaultValue: "",
  },
  {
    name: "group",
    label: "Grupp",
    description:
      "Grupeerib moodulid (nt kasutajaliides, äriloogika, andmebaas), mis moodustavad ühe rakenduse.",
    type: z.string(),
    defaultValue: "",
  },
  {
    name: "type",
    label: "Tüüp",
    description:
      "Rakenduse tüüp või kategooria (java; php; andmebaasimootor; ...).",
    type: z.string(),
    defaultValue: "",
  },
  {
    name: "description",
    label: "Kirjeldus",
    description: "Rakenduse kirjeldus. Kuni 20000 tähemärki.",
    type: z.string().trim().max(20000),
    defaultValue: "",
  },
  {
    name: "cost",
    label: "Arenduskulu",
    description: "Rakenduse arenduskulu.",
    type: z.literal("").or(z.coerce.number().int().gte(0)),
    defaultValue: "",
  },
];

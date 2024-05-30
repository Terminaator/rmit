import { z } from "zod";
import { DataFormFieldType } from "@/components/data/Form/type";

const TYPE = ["HTTP", "SAML", "SSH", "JDBC", "ODBC"] as const;
const SUB_TYPE = ["REST", "SOAP"] as const;

export const createServiceFields: DataFormFieldType[] = [
  {
    name: "appCode",
    label: "Rakenduse kood",
    description: "Viitab Application tabeli kirjele.",
    type: "INPUT",
    validation: z.string().uuid(),
    defaultValue: "",
  },
  {
    name: "name",
    label: "Nimi",
    description: "Teenuse inimloetav nimetus.",
    type: "INPUT",
    validation: z.string().trim().min(1).max(255),
    defaultValue: "",
  },
  {
    name: "type",
    label: "Tüüp",
    description: "HTTP; SAML; SSH; JDBC; ODBC;",
    type: "SELECT",
    validation: z.literal("").or(z.enum(TYPE)),
    defaultValue: "",
    options: TYPE,
  },
  {
    name: "subType",
    label: "Alamtüüp",
    description: "REST; SOAP;",
    type: "SELECT",
    validation: z.literal("").or(z.enum(SUB_TYPE)),
    defaultValue: "",
    options: SUB_TYPE,
  },
  {
    name: "description",
    label: "Kirjeldus",
    description: "Teenuse kirjeldus.",
    type: "INPUT",
    validation: z.string().trim().max(20000),
    defaultValue: "",
  },
];

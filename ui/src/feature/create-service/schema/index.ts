import { z } from "zod";

export const createServiceFields = [
  {
    name: "appCode",
    label: "Rakenduse kood",
    description: "Viitab Application tabeli kirjele.",
    type: z.string().uuid(),
    defaultValue: "",
  },
  {
    name: "name",
    label: "Nimi",
    description: "Teenuse inimloetav nimetus.",
    type: z.string().trim().min(1).max(255),
    defaultValue: "",
  },
  {
    name: "type",
    label: "Tüüp",
    description: "HTTP; SAML; SSH; JDBC; ODBC;",
    type: z.string(),
    defaultValue: "",
  },
  {
    name: "subType",
    label: "Alamtüüp",
    description: "REST; SOAP;",
    type: z.string(),
    defaultValue: "",
  },
  {
    name: "description",
    label: "Kirjeldus",
    description: "Teenuse kirjeldus.",
    type: z.string().trim().max(20000),
    defaultValue: "",
  },
];

"use client";

import React from "react";
import { createServiceAction } from "@/feature/create-service/action";
import { createServiceSchema } from "@/feature/create-service/schema";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { SERVICE_SUB_TYPE, SERVICE_TYPE } from "@/feature/create-service/type";
import { DataFormFieldType } from "@/components/data/FormField";
import { DataDialog } from "@/components/data/Dialog";

const createServiceFields: DataFormFieldType[] = [
  {
    name: "appCode",
    label: "Rakenduse kood",
    description: "Viitab Application tabeli kirjele.",
    type: "INPUT",
  },
  {
    name: "name",
    label: "Nimi",
    description: "Teenuse inimloetav nimetus.",
    type: "INPUT",
  },
  {
    name: "type",
    label: "Tüüp",
    description: "HTTP; SAML; SSH; JDBC; ODBC;",
    type: "SELECT",
    options: SERVICE_TYPE,
  },
  {
    name: "subType",
    label: "Alamtüüp",
    description: "REST; SOAP;",
    type: "SELECT",
    options: SERVICE_SUB_TYPE,
  },
  {
    name: "description",
    label: "Kirjeldus",
    description: "Teenuse kirjeldus.",
    type: "INPUT",
  },
];

export const CreateService = () => {
  const form = useForm<z.infer<typeof createServiceSchema>>({
    resolver: zodResolver(createServiceSchema),
    defaultValues: {
      appCode: "",
      name: "",
      type: undefined,
      subType: undefined,
      description: "",
    },
  });

  return (
    <DataDialog
      title={"Uue teenuse sisestamine"}
      submit={createServiceAction}
      form={form}
      fields={createServiceFields}
    />
  );
};

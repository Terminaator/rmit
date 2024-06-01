"use client";

import { createApplicationAction } from "@/feature/create-application/action";
import { createApplicationSchema } from "@/feature/create-application/schema";
import React from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  APPLICATION_GROUP_TYPE,
  APPLICATION_TYPE,
} from "@/feature/create-application/type";
import { DataFormFieldType } from "@/components/data/FormField";
import { DataDialog } from "@/components/data/Dialog";

export const createApplicationFields: DataFormFieldType[] = [
  {
    name: "name",
    label: "Nimi",
    description: "Rakenduse inimloetav nimetus.",
    type: "INPUT",
  },
  {
    name: "group",
    label: "Grupp",
    description:
      "Grupeerib moodulid (nt kasutajaliides, äriloogika, andmebaas), mis moodustavad ühe rakenduse.",
    type: "SELECT",
    options: APPLICATION_GROUP_TYPE,
  },
  {
    name: "type",
    label: "Tüüp",
    description:
      "Rakenduse tüüp või kategooria (java; php; andmebaasimootor; ...).",
    type: "SELECT",
    options: APPLICATION_TYPE,
  },
  {
    name: "description",
    label: "Kirjeldus",
    description: "Rakenduse kirjeldus. Kuni 20000 tähemärki.",
    type: "INPUT",
  },
  {
    name: "cost",
    label: "Arenduskulu",
    description: "Rakenduse arenduskulu.",
    type: "INPUT",
  },
];

export const CreateApplication = () => {
  const form = useForm<z.infer<typeof createApplicationSchema>>({
    resolver: zodResolver(createApplicationSchema),
    defaultValues: {
      name: "",
      group: undefined,
      type: undefined,
      description: "",
      cost: "",
    },
  });

  return (
    <DataDialog
      title={"Uue rakenduse sisestamine"}
      submit={createApplicationAction}
      form={form}
      fields={createApplicationFields}
    />
  );
};

"use client";

import { createApplicationAction } from "@/feature/create-application/action";
import { createApplicationFields } from "@/feature/create-application/schema";
import React from "react";
import { DataFormDialog } from "@/components/data/FormDialog";

const dialog = {
  title: "Uue rakenduse sisestamine",
  form: {
    action: createApplicationAction,
    fields: createApplicationFields,
  },
};

export const CreateApplication = () => {
  return <DataFormDialog title={dialog.title} form={dialog.form} />;
};

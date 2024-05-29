import React from "react";
import { Application } from "@/types";
import { Suspense } from "react";
import { ApplicationListProps } from "@/feature/application-list/type";
import { getApplicationsAction } from "@/feature/application-list/action";
import { DataTable } from "@/components/data/Table";
import { DataSearch } from "@/components/data/Search";

const table = {
  get: getApplicationsAction,
  columns: [
    {
      header: "Kood",
      value: (application: Application) => application.code,
    },
    {
      header: "Nimi",
      value: (application: Application) => application.name,
    },
    {
      header: "Grupp",
      value: (application: Application) => application.group,
    },
    {
      header: "Tüüp",
      value: (application: Application) => application.type,
    },
    {
      header: "Arenduskulu",
      value: (application: Application) => application.cost,
    },
    {
      header: "Muudetud",
      value: (application: Application) => application.modified,
    },
  ],
  caption: "Teadmata kadunud rakendused...",
  searchParamName: "name",
};

export const ApplicationList = ({ params }: ApplicationListProps) => {
  return (
    <div className="flex flex-1 flex-col gap-4 p-4 lg:gap-6 lg:p-6">
      <Suspense>
        <DataSearch placeholder={"Otsi rakendust teenuse nime järgi..."} />
      </Suspense>

      <div className="flex items-center">
        <DataTable params={params} {...table} />
      </div>
    </div>
  );
};

import { Params } from "@/types";
import { DataTitle } from "@/components/data/Title";
import { CreateApplication } from "@/feature/create-application/container";
import { ApplicationList } from "@/feature/application-list/container";
import React from "react";

type HomeProps = {
  searchParams: Params;
};

export default function Home({ searchParams }: HomeProps) {
  return (
    <main>
      <DataTitle value={"Rakendused"}>
        <CreateApplication />
      </DataTitle>

      <ApplicationList params={searchParams} />
    </main>
  );
}

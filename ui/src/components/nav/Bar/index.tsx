"use server";

import Image from "next/image";
import React from "react";
import { NavLink } from "@/components/nav/Link";

const links = [
  {
    href: "/",
    name: "Rakendused",
  },
  {
    href: "/service",
    name: "Teenused",
  },
];

export const NavBar = async () => {
  return (
    <div className="hidden border-r bg-muted/40 md:block">
      <div className="flex h-full max-h-screen flex-col gap-2">
        <div className="flex h-14 items-center border-b px-4 lg:h-[60px] lg:px-6">
          <h1 className="flex items-center gap-2 font-semibold">RMIT</h1>

          <div className="ml-auto h-8 w-8">
            <Image
              alt={"RMIT logo"}
              src={
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAsElEQVR4Ae3VsQnFIBCAYavXvT1ugdSWadK7RmrXSOsIrpExsoE7XC5wNkcUi+MgYPFXwn3Cgbp130z7Dugc/qxBpA5rsBZMQe6iQAVkID4JMFJZwLm33xHIU6UOezk/KBStHbAJ/alTDEp8BnwJrwn6l0GJMeRMwGWCE6xNsFBgBcbG66MKApXF442iFggav0UZAGNnhhqYqLN+SZpgGtiVCij3elmAEg4MBgOw3wRvRjDSUIPayQMAAAAASUVORK5CYII="
              }
              width={40}
              height={40}
            />
          </div>
        </div>
        <div className="flex-1">
          <nav className="grid items-start px-2 text-sm font-medium lg:px-4">
            {links.map((link) => (
              <NavLink key={link.href} {...link} />
            ))}
          </nav>
        </div>
      </div>
    </div>
  );
};

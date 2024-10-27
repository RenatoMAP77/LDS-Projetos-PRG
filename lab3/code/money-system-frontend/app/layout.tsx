import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";
import SidebarComponent from "@/components/root/Sidebar";
import { Toaster } from "@/components/ui/toaster";
import { EntidadeProvider } from "@/context/EntidadeContext";

const geistSans = localFont({
  src: "./fonts/GeistVF.woff",
  variable: "--font-geist-sans",
  weight: "100 900",
});
const geistMono = localFont({
  src: "./fonts/GeistMonoVF.woff",
  variable: "--font-geist-mono",
  weight: "100 900",
});

export const metadata: Metadata = {
  title: "Sistema de Moedas Estudantil",
  description: "Laboratório 3 - Engenharia de Software PUC Minas",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        <EntidadeProvider>
          <SidebarComponent children={children} />
          <Toaster />
        </EntidadeProvider>
      </body>
    </html>
  );
}

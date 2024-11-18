import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";
import { EntidadeProvider } from "@/context/EntidadeContext";
import { VantagemProvider } from "@/context/VantagemContext";


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
                className={`${geistSans.variable} ${geistMono.variable} antialiased `}
            >
                <EntidadeProvider>
                    <VantagemProvider>
                        {children}
                    </VantagemProvider>
                </EntidadeProvider>
            </body>
        </html>
    );
}

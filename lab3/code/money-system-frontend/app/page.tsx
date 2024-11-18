"use client";
import HomePage from "@/components/root/HomePage";

export default function Page() {
    localStorage.setItem("type", "");

    return (
        <div className="container mx-auto px-4 py-8">
            <HomePage />
        </div>
    );
}

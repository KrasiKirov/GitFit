/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'custom-lightlight-blue': '#E1E5F8',
        'custom-light-blue': '#BBC4EB',
        'custom-blue': '#737CCF',
        'custom-dark-blue': '#2218A7',
      }
    },
  },
  plugins: [],
}


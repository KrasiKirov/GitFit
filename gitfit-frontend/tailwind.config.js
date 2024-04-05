/** @type {import('tailwindcss').Config} */
export default {
  content: [
    './public/**/*.html',
    './src/**/*.{js,jsx,ts,tsx,vue}',
  ],
  theme: {
    extend: {
      colors: {
        moodyblue: '#737CCF',
        linkwater: '#E1E5F8',
        persianblue: '#2218A7',
        spindle: '#BBC4EB',
      }
    },
  },
  plugins: [],
}


import { DEFAULT_VERSION } from "redux-persist";

export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
          theme: '#EE57CD',
          semi_theme: '#FEEFFA',
          kakao: '#FFDC00',
          action: '#ff3437',
      },
      fontFamily: {
        roboto: ['Roboto', 'sans-serif'],
      },

    },
  },
  plugins: [],
};
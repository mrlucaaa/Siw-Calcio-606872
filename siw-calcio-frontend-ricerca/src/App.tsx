import { ThemeProvider, createTheme, CssBaseline } from '@mui/material';
import RicercaGiocatori from './components/RicercaGiocatori';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2',
    },
    secondary: {
      main: '#dc004e',
    },
  },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <RicercaGiocatori />
    </ThemeProvider>
  );
}

export default App;

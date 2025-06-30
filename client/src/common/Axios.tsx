import axios from 'axios';
import { store } from './store/store';


const baseURL = 'http://192.168.0.121:8090';


export function api(withAuth: boolean) {

  const headers: Record<string, string> = {
    'Content-Type': 'application/json',
  };

  if (withAuth) {
    const accessToken = store.getState().auth.accessToken;
    headers['Authorization'] = `Bearer ${accessToken}`;
  }


  return axios.create({
    baseURL: baseURL,
    timeout: 5000,
    headers
    }
  )
}


export function apiForm(withAuth: boolean) { 
  
    const headers: Record<string, string> = {
    'Content-Type': 'multipart/form-data',
  };
  
  if (withAuth) {
    const accessToken = store.getState().auth.accessToken;
    headers['Authorization'] = `Bearer ${accessToken}`;
  }
  
  return axios.create({
    baseURL: baseURL,
    timeout: 5000,
    headers
    }
  )
}

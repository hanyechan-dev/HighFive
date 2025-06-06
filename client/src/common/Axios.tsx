import axios from 'axios';
import { store } from '../app/store';

const baseURL = 'http://localhost:8090';


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

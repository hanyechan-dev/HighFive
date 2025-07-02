import axios from 'axios';
import { store } from './store/store';


const baseURL = '/api';


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

export function apiLong(withAuth: boolean) {

  const headers: Record<string, string> = {
    'Content-Type': 'application/json',
  };

  if (withAuth) {
    const accessToken = store.getState().auth.accessToken;
    headers['Authorization'] = `Bearer ${accessToken}`;
  }


  return axios.create({
    baseURL: baseURL,
    timeout: 60000,
    headers
    }
  )
}


export function apiForm(withAuth: boolean) { 
  
    const headers: Record<string, string> = {};
  
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

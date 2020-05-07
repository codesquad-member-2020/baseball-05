import axios from 'axios';

function createInstance() {
  return axios.create({
    baseURL: process.env.VUE_APP_BASE_MOCKDATA,
  });
}

export const instance = createInstance();

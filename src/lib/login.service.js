import axios from 'axios';
// const baseUrl = '/api/login';
//For development
axios.defaults.baseURL = `http://10.49.36.22:9008/api/login`;

const login = async (credentials) => {
  const response = await axios.post(baseUrl, credentials);

  return response.data;
};

export default { login };

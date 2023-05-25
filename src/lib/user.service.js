import axios from 'axios';

const baseURL = 'http://10.49.36.22:9008/api/register';

const create = async (userData) => {
  console.log(userData);
  const response = await axios.post(baseURL, userData);

  console.log(response);
  return response.data;
};

export default { create };

'use client';
import { useState, useEffect } from 'react';
import Select from './Select';

const Wrapper = () => {
  const [countries, setCountries] = useState([]);
  const [country0, setCountry0] = useState('');
  const [country1, setCountry1] = useState('');
  useEffect(() => {
    const getCountries = async () => {
      const { data } = await axios.get('http://localhost:3004/countries');

      const mappedCountries = data.map((country) => ({
        country: country.Country,
        id: country._id.$oid,
      }));

      console.log(mappedCountries);
      setCountries(mappedCountries);
    };
    getCountries();

    console.log(countries);
  }, []);
  return (
    <div>
      <Select value={country0} onChange={(e) => setCountry0(e.target.value)}>
        {countries.map((country) => {
          return (
            <option key={country.id} value={country.id}>
              {country.country}
            </option>
          );
        })}
      </Select>
      <Select value={country1} onChange={(e) => setCountry1(e.target.value)}>
        {countries.map((country) => {
          return (
            <option key={country.id} value={country.id}>
              {country.country}
            </option>
          );
        })}
      </Select>
    </div>
  );
};

export default Wrapper;

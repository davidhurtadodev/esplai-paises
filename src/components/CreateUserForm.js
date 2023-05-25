'use client';
import axios from 'axios';
import { useState, useEffect } from 'react';
import Input from './Input';
import Select from './Select';
import userService from '@/lib/user.service';
import Link from 'next/link';

const CreateUserForm = () => {
  const [newUsername, setNewUsername] = useState('');
  const [newName, setNewName] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [country, setCountry] = useState('');
  const [countries, setCountries] = useState([]);

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
  }, []);

  const handleCreateUserSubmit = async (e) => {
    e.preventDefault();

    const response = await userService.create({
      name: newName,
      email: newUsername,
      password: newPassword,
      country: country,
    });

    setNewUsername('');
    setNewPassword('');
    setNewName('');
    setCountry('');
  };

  return (
    <form>
      <div className="mb-3">
        <label for="exampleInputEmail1" className="form-label">
          Name
        </label>
        <Input onChange={(e) => setNewName(e.target.value)} />
      </div>
      <div className="mb-3">
        <label for="exampleInputEmail1" className="form-label">
          Email
        </label>
        <Input type="email" onChange={(e) => setNewUsername(e.target.value)} />
      </div>
      <div className="mb-3">
        <label for="exampleInputPassword1" className="form-label">
          Country
        </label>

        <Select value={country} onChange={(e) => setCountry(e.target.value)}>
          {countries.map((country) => {
            return (
              <option key={country.id} value={country.id}>
                {country.country}
              </option>
            );
          })}
        </Select>
      </div>
      <div className="mb-3">
        <label for="exampleInputPassword1" className="form-label">
          Password
        </label>
        <Input
          type="password"
          onChange={(e) => setNewPassword(e.target.value)}
        />
      </div>

      <button
        type="submit"
        onClick={handleCreateUserSubmit}
        className="btn btn-primary mb-5"
      >
        Submit
      </button>
      <Link href="/info">Enter</Link>
    </form>
  );
};

export default CreateUserForm;

import { useState } from 'react';
import axios from 'axios';

const YelpData = () => {
  const [businessName, setBusinessName] = useState('');

  const getSimilarBusinesses = async (): Promise<void> => {
    const res = await axios.get(
      `http://localhost:8080/yelpdata/similar/${businessName}`
    );
    console.log(res.data);
  };

  const getAllBusinesses = async (): Promise<void> => {
    const res = await axios.get('http://localhost:8080/yelpdata/allBusinesses');

    console.log('res.data');
  };

  return (
    <div className='bg-violet-200 min-h-screen w-full flex justify-center'>
      <div className='flex justify-center flex-col items-center'>
        <input
          type='text'
          className='my-2 border-[1px] border-green-400'
          placeholder='Business Name...'
          name='businessName'
          value={businessName}
          onChange={(e) => setBusinessName(e.target.value)}
        />
        <button
          className='bg-blue-500 py-2 px-5 border-2 rounded-lg  '
          onClick={getSimilarBusinesses}
        >
          {' '}
          Get Similar Businessses{' '}
        </button>
        <button
          className='bg-blue-500 py-2 px-5 border-2 rounded-lg  '
          onClick={getAllBusinesses}
        >
          {' '}
          Get All Businessses{' '}
        </button>
      </div>
    </div>
  );
};

export default YelpData;

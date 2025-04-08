import axios from 'axios';
import { useForm } from 'react-hook-form';

type EmailFormInputs = {
  email: string;
  name: string;
};

function EmailForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<EmailFormInputs>();

  const onSubmit = async (data: EmailFormInputs) => {
    try {
      const response = await axios.post('http://localhost:8080/register', {
        email: data.email,
        name: data.name,
      });
      console.log('서버응답', response.data);
      alert('인증 이메일이 발송 되었습니다.');
    } catch (error) {
      console.error(error, '이메일 인증 요청 실패');
      alert('이메일 요청 실패');
    }
  };

  return (
    <div style={{ padding: '2rem' }}>
      <h2>이메일 인증</h2>
      <form onSubmit={handleSubmit(onSubmit)}>
        <input
          type="email"
          placeholder="이메일 주소를 입력하세요"
          {...register('email', {
            required: '이메일은 필수입니다.',
            pattern: {
              value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
              message: '올바른 이메일 형식을 입력해주세요.',
            },
          })}
        />
        <br />
        {errors.email && <span style={{ color: 'red' }}>{errors.email.message}</span>}
        <br />
        <button type="submit">인증 메일 보내기</button>
      </form>
    </div>
  );
}

export default EmailForm;

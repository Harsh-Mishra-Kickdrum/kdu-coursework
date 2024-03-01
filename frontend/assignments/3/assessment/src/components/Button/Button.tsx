//create a button component
import * as S from "./Button.styles";
import React from "react";


export const Button: React.FC<{ title: string }> = ({ title }) => {
    return (
      <>
        <S.Button>
          <button className="Button">
            <h1>{title}</h1>
          </button>
        </S.Button>
      </>
    );
}